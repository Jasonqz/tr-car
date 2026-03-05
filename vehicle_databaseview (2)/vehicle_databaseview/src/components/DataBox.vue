<script setup></script>

<template>
  <div class="small-box">
    <span class="title-top"></span>
    <div class="data-box">
      <h3 class="title"><slot name="title">这里是标题</slot></h3>
      <div class="data">
        <slot name="data">6609</slot>&nbsp;
        <span class="unit"><slot name="unit">元</slot></span>
      </div>
    </div>
  </div>
</template>

<style scoped>
.small-box {
  margin: 0.1rem;
  position: relative;
  margin-bottom: 0.2rem;
  margin-top: 0.2rem;
}

.data-box {
  position: relative;
  width: 3.5rem;
  height: 1.7rem;
  clip-path: polygon(0 0, 90% 0, 100% 20%, 100% 100%, 10% 100%, 0 80%);
  background-color: rgb(21, 70, 93, 0.7);
  padding: 0.2rem;
  border: 1px solid rgba(14, 253, 255, 0.5);
}

.title-top {
  display: block;
  height: 0.17rem;
  width: 1.3rem;
  clip-path: polygon(10% 0, 90% 0, 100% 10%, 100% 100%, 0 100%, 0 10%);
  background-color: rgb(21, 70, 93, 0.5);
  background: url(../assets/title_left_bg.png) no-repeat;
  background-size: contain;
  position: absolute;
  top: -0.15rem;
  left: 0;
  z-index: 1;
}

.data-box::before {
  content: '';
  position: absolute;
  height: 0.35rem;
  width: 0.35rem;
  bottom: -1px;
  left: -1px;
  background: linear-gradient(
    45deg,
    transparent 48%,
    rgba(14, 253, 255, 0.5) 48%,
    rgba(14, 253, 255, 0.5) 52%,
    transparent 52%
  );
}

.data-box::after {
  content: '';
  position: absolute;
  height: 0.35rem;
  width: 0.35rem;
  top: -1px;
  right: -1px;
  background: linear-gradient(
    45deg,
    transparent 48%,
    rgba(14, 253, 255, 0.5) 48%,
    rgba(14, 253, 255, 0.5) 52%,
    transparent 52%
  );
}

.data-box .title {
  color: white;
}

@font-face {
  font-family: 'UnidreamLED';
  src: url(../assets/Digiface-Regular.ttf);
}

.data-box .data {
  font-size: 0.7rem;
  color: aqua;
  font-family: 'UnidreamLED';
  font-weight: 700;
}

.data-box .data .unit {
  font-size: 0.3rem;
  font-family: 'Courier New', Courier, monospace;
}
</style>
