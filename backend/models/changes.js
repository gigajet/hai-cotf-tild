const mongoose=require('mongoose')
const dateutil=require('../utils/date')

const changeSchema=new mongoose.Schema({
  amount: {
    type: mongoose.Schema.Types.Int32,
    required: true,
  },
  reason: {
    type: String,
    default: '',
  },
  date: Date
})

changeSchema.set('toJSON', {
  transform: (doc, ret, options)=>{
    ret.id=ret._id.toString()
    ret.date=dateutil.formatDate(ret.date)
    delete ret._id
    delete ret.__v
    return ret
  }
})

const LeftChange=mongoose.model('Left-Change', changeSchema)
const RightChange=mongoose.model('Right-Change', changeSchema)

module.exports={LeftChange, RightChange}
