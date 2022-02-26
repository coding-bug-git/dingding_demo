import axios from 'axios';

const proxyUrl = '/proxy/index'
console.log(123123)
axios.get(proxyUrl).then(result => {
  console.log(result)
}, err => {
  console.log(err)
})