require('dotenv').config()
const express=require('express')
require('express-async-errors')
const cors=require('cors')
const mongoose=require('mongoose')
const Change = require('./models/changes')
const morgan=require('morgan')
const app=express()

mongoose.connect(process.env.MONGODB_URI)
  .then(()=>{
    console.log(`Connected to ${process.env.MONGODB_URI}`)
  }).catch(e=>{
    console.log(`Mongoose connection error ${e.name}: ${e.message}`)
  })

const router=express.Router()

const calcTotal=changes=>{
  return changes.reduce((acc, change)=>{
    return acc+change.amount
  }, 0)
}

router.get('/', async (req,res)=>{
  const changes=await Change.find({})
  res.json({
    total: calcTotal(changes),
    changes,
  })
})

router.get('/:year/:month', async(req,res)=>{
  let {year, month}=req.params
  year=Number(year); month=Number(month)
  console.log(year,month,year===2025,month===1)
  const changes=await Change.find({})
  const totalBefore=changes.reduce((acc, change)=>{
    if (change.date.getFullYear()>year 
      || (change.date.getFullYear()===year && change.date.getMonth()+1>=month)) {
        return acc
    } else {
        return acc+change.amount
      }
  },0)
  const changesInMonth=changes.filter((c)=>{
    return c.date.getFullYear()===year && c.date.getMonth()+1===month
  })
  const retChanges=[ 
    new Change({
      amount: totalBefore,
      reason: `Tính tới đầu tháng ${month} năm ${year}`,
      date: new Date(`${year}-${month}-01`),
    })
  ].concat(changesInMonth)
  res.json({
    total: calcTotal(retChanges),
    changes: retChanges,
  })
})

router.post('/', async (req,res)=>{
  const change=new Change(req.body)
  await change.save()
  res.status(201).json(change)
})

router.delete('/:id', async (req,res)=>{
  await Change.findByIdAndDelete(req.params.id)
  res.status(204).end()
})

const errorHandler=async (err,req,res,next)=>{
  console.log(`Error ${err.name}: ${err.message}`)
  res.status(400).json({'error': `bad request leading to ${err.name}: ${err.message}`})
}

const unknownEndpoint=async (req,res)=>{
  res.status(404).send('404 unknown endpoint').end()
}

app.use(cors())
app.use(express.json())
app.use(morgan('tiny'))
app.use('/api/change', router)
app.use(errorHandler)
app.use(unknownEndpoint)

const PORT=process.env.PORT || 3000
app.listen(PORT, ()=>{
  console.log(`App listening on port ${PORT}`)
})
