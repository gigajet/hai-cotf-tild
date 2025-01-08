
const formatDate=date=>{
  const dow=date.getDay()
  let dowPart='CN'
  if (dow>0) {
    dowPart=`T${dow+1}`
  }
  return `${dowPart} ${date.getDate()}/${date.getMonth()+1}/${date.getFullYear()}`
}

module.exports={formatDate}
