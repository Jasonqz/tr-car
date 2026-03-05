<script setup>
import { ref } from 'vue'
let level = ref()
// const list = ['优', '良', '轻', '中', '重', '严']
const colorList = ['#078b04', '#e8da07', '#db770c', '#d6070a', '#991652', '#641326']
let count = ref(0)
const loop = () => {
  count = ref(Math.floor(Math.random() * 101))
  if (count.value < 20 && count.value > 0) {
    level.value.innerText = `${count.value}`
    level.value.style.color = `${colorList[0]}`
  } else if(count.value < 40 && count.value >= 20){
    level.value.innerText = `${count.value}`
    level.value.style.color = `${colorList[1]}`
  } else if(count.value < 60 && count.value >= 40){
    level.value.innerText = `${count.value}`
    level.value.style.color = `${colorList[2]}`
  } else if(count.value < 80 && count.value >= 60){
    level.value.innerText = `${count.value}`
    level.value.style.color = `${colorList[3]}`
  } else if(count.value < 100 && count.value >= 80){
    level.value.innerText = `${count.value}`
    level.value.style.color = `${colorList[4]}`
  } else if(count.value >= 100){
    level.value.innerText = `${count.value}`
    level.value.style.color = `${colorList[5]}`
  }
}
setInterval(loop, 3000)
</script>

<template>
  <div class="box">
    <div class="head">
      <div class="level" ref="level">0</div>
      <div class="info">
        <h3>车速单位:km/h</h3>
        <h3>加速度单位：m/s<sup>2</sup></h3>
        <h3>地点：重庆邮电大学</h3>
      </div>
    </div>
    <div class="main">
      <div class="excellent style3">
        <div class="color"></div>
        <div class="text">1档</div>
      </div>
      <div class="good style3">
        <div class="color"></div>
        <div class="text">2档</div>
      </div>
      <div class="mild style3">
        <div class="color"></div>
        <div class="text">3档</div>
      </div>
      <div class="moderate style3">
        <div class="color"></div>
        <div class="text">4档</div>
      </div>
      <div class="severe style3">
        <div class="color"></div>
        <div class="text">5档</div>
      </div>
      <div class="serious style3">
        <div class="color"></div>
        <div class="text">6档</div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.box {
  width: 100%;
  height: 100%;
}

.head {
  width: 100%;
  height: 55%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.head .level {
  flex: 1.5;
  text-align: center;
  font-size: 1.25rem;
  color: white;
}

.head .info {
  flex: 2;
  color: white;
  padding-left: 0.1rem;
  text-align: center;
}

.main {
  width: 100%;
  height: 45%;
  display: flex;
  padding: 0 0.1rem;
}

.style3 {
  flex: 1;
}

.main .excellent {
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
}

.main .excellent .color {
  flex: 1;
  background-color: #078b04;
  width: 100%;
  border-radius: 5%;
}

.main .excellent .text {
  flex: 3;
  text-align: center;
  color: white;
  padding-top: 0.2rem;
  font-size: 0.3rem;
  font-weight: 700;
}

.main .good {
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
}

.main .good .color {
  flex: 1;
  background-color: #e8da07;
  width: 100%;
  border-radius: 5%;
}

.main .good .text {
  flex: 3;
  text-align: center;
  color: white;
  padding-top: 0.2rem;
  font-size: 0.3rem;
  font-weight: 700;
}

.main .mild {
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
}

.main .mild .color {
  flex: 1;
  background-color: #db770c;
  width: 100%;
  border-radius: 5%;
}

.main .mild .text {
  flex: 3;
  text-align: center;
  color: white;
  padding-top: 0.2rem;
  font-size: 0.3rem;
  font-weight: 700;
}

.main .moderate {
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
}

.main .moderate .color {
  flex: 1;
  background-color: #d6070a;
  width: 100%;
  border-radius: 5%;
}

.main .moderate .text {
  flex: 3;
  text-align: center;
  color: white;
  padding-top: 0.2rem;
  font-size: 0.3rem;
  font-weight: 700;
}

.main .severe {
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
}

.main .severe .color {
  flex: 1;
  background-color: #991652;
  width: 100%;
  border-radius: 5%;
}

.main .severe .text {
  flex: 3;
  text-align: center;
  color: white;
  padding-top: 0.2rem;
  font-size: 0.3rem;
  font-weight: 700;
}

.main .serious {
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
}

.main .serious .color {
  flex: 1;
  background-color: #641326;
  width: 100%;
  border-radius: 5%;
}

.main .serious .text {
  flex: 3;
  text-align: center;
  color: white;
  padding-top: 0.2rem;
  font-size: 0.3rem;
  font-weight: 700;
}
</style>
