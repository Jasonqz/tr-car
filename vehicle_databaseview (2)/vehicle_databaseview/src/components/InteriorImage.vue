<script setup></script>

<template>
  <div class="box">
    <div class="title">内室图像</div>
    <div class="interior-image">
      <img src="@\assets\QQ20241104-152741.png">
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

.interior-image img {
  width: 100%;
  height: 3rem;
  background-repeat: no-repeat;
  background-size: contain;
}
</style>
