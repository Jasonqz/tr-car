<!-- 这个代码显示网页的年月日时分秒 -->

<script setup>
import { ref } from 'vue'
defineOptions({
  name: 'MyHeader'
})
const timeList = ['星期天', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六']
const date = new Date()               // Date 是JS内建的时间类
let year = ref(date.getFullYear())
let month = ref(date.getMonth())
let day = ref(date.getDate())
let hour = ref(date.getHours())
let minute = ref(date.getMinutes())
let second = ref(date.getSeconds())
let daytime = ref(date.getDay())
const timeFormat = (time) => {
  time.value = time.value >= 10 ? time.value : '0' + time.value
  return time.value
}
const timer = () => {
  const date = new Date()
  year.value = ref(date.getFullYear())
  month.value = timeFormat(ref(date.getMonth() + 1))
  day.value = timeFormat(ref(date.getDate()))
  hour.value = timeFormat(ref(date.getHours()))
  minute.value = timeFormat(ref(date.getMinutes()))
  second.value = timeFormat(ref(date.getSeconds()))
  daytime.value = timeList[ref(date.getDay()).value]
}
setInterval(timer, 1000)
</script>

<template>
  <div class="header">
    <div class="title">
      <div class="title-left">
        {{ year }} - {{ month }} - {{ day }}&nbsp;&nbsp;{{ hour }} : {{ minute }} : {{ second }}
      </div>
      <div class="title-center">
        <div>数据可视化展示</div>
        <div class="header-img"></div>
      </div>
      <div class="title-right"></div>
    </div>
    <div class="title-bottom"></div>
  </div>
</template>

<style scoped>
@font-face {
  /* 设置自定义字体 */
  font-family: 'UnidreamLED';
  src: url(../assets/Digiface-Regular.ttf);
}

.header {
  height: 1.35rem;
}

.title {
  display: flex;
}

.title .title-left {
  text-align: center;
  flex: 1;
  height: 1.05rem;
  color: #fff;
  line-height: 1.05rem;
  font-size: 0.35rem;
  font-family: 'UnidreamLED';
}

.title .title-center {
  position: relative;
  text-align: center;
  color: #fff;
  text-shadow: 0 0 0.8rem #00d8ff;
  flex: 2;
  font-size: 0.4rem;
  line-height: 1.04rem;
}

.title .title-center .header-img {
  background: url('../assets/head.gif') no-repeat center;
  background-size: 100%;
  width: 100%;
  height: 100%;
  position: absolute;
  top: 0.4rem;
}

.title .title-right {
  flex: 1;
}

.title-bottom {
  width: calc(100% - 0.4rem);
  height: 0.16rem;
  background: url('../assets/header.png') no-repeat;
  background-size: calc(100% - 0.2rem) 100%;
  padding: 0 0.2rem;
  margin-left: 0.3rem;
}
</style>
