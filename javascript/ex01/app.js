const { fail } = require('assert');
const express = require('express');
const { resolve } = require('path');
const { setTimeout } = require('timers/promises');
const app = express()
const port = 3000

function execute(resolve, reject) {
  console.log("작업수행!");
  reject();
}

function success()  {
  console.log("sucess!");
}

function final() {
  console.log("completed!");
}

app.get('/hello', async (req, res) => {
  

app.get('/exam05_1', async(req, res) => {
  await new Promise((resolve,reject) => {setTimeout(resolve,10000)})
  res.send('console.log("exam05_1 ok!");')
})

app.get('/exam05_2', (req, res) => {
    res.send('console.log("exam05_2 ok!")')
})

app.get('/exam05_x', async (req, res) => {
  await new Promise(resolve => setTimeout(resolve,1000));
  res.send('var rate = 30000;')
})

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`)
})

