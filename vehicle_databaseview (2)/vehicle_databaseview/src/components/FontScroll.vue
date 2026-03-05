<script setup>
const list = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
const colorList = [
  'red',
  'pink',
  'blue',
  'green',
  'purple',
  '#59ebe8',
  'yellow',
  'brown',
  'orange',
  'black'
]
</script>

<template>
  <div class="box">
    <div class="title">数据可视化测试</div>
    <div class="small-header">
      <div class="small-title">排名</div>
      <div class="small-title">同比</div>
      <div class="small-title">数量</div>
    </div>
    <div class="container">
      <ul class="scroll">
        <li v-for="(item, index) in list" :key="item">
          <div class="style2">
            <span class="tag" :style="{ backgroundColor: colorList[index] }">{{ item }}</span>
          </div>
          <div class="style2">{{ item }}</div>
          <div class="style2">{{ item }}</div>
        </li>
        <li v-for="(item, index) in list" :key="item">
          <div class="style2">
            <span class="tag" :style="{ backgroundColor: colorList[index] }">{{ item }}</span>
          </div>
          <div class="style2">{{ item }}</div>
          <div class="style2">{{ item }}</div>
        </li>
      </ul>
    </div>
  </div>
</template>

<style scoped>
.box {
  padding: 0 0.2rem;
  font-size: 0.25rem;
  height: 100%;
  width: 100%;
}

.title:before {
  width: 0.05rem;
  height: 0.25rem;
  /* top: 0.02rem; */
  position: absolute;
  content: '';
  background: #59ebe8;
  border-radius: 0.02rem;
  left: 0;
}

.title {
  width: 90%;
  height: 0.3rem;
  /* border: 1px solid gold; */
  position: relative;
  top: 0.1rem;
  /* left: 0.25rem; */
  color: white;
  padding-left: 0.12rem;
  line-height: 0.3rem;
  margin-bottom: 0.2rem;
}

.small-header {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 0.1rem;
}

.small-header .small-title {
  flex: 1;
  text-align: center;
  color: #61d2f7;
  font-size: 0.2rem;
}

.container {
  height: 80%;
  overflow: hidden;
}

.scroll {
  display: flex;
  flex-wrap: wrap;
  animation: 10s move infinite linear;
}

.scroll:hover {
  animation-play-state: paused;
}

.scroll li {
  width: 100%;
  height: 0.5rem;
  display: flex;
  justify-content: center;
  align-items: center;
}

.scroll li:hover {
  box-shadow:
    -10px 0px 15px #034c6a inset,
    /*左边阴影*/ 10px 0px 15px #034c6a inset;
  cursor: pointer;
}

.style2 {
  flex: 1;
  height: 100%;
  /* background-color: pink; */
  margin: 0.1rem;
  display: flex;
  justify-content: center;
  align-items: center;
  color: white;
}

.tag {
  display: block;
  height: 0.4rem;
  width: 0.4rem;
  border-radius: 15%;
  text-align: center;
  line-height: 0.38rem;
  background-color: pink;
  font-size: 0.28rem;
  color: #fff;
  font-weight: 700;
}

@keyframes move {
  0% {
    transform: translateY(0);
  }

  100% {
    transform: translateY(-50%);
  }
}
</style>
