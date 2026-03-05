// src/main.ts
import { createApp } from 'vue'
import App from './App.vue'

// 1. 引入 Element Plus 的样式
import 'element-plus/dist/index.css'
// 2. 引入 Element Plus 插件
import ElementPlus from 'element-plus'
// 3. 引入图标注册逻辑 (可选，为了防止报错，先把所有图标注册了)
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

const app = createApp(App)

// 4. 使用 Element Plus
app.use(ElementPlus)

// 5. 注册所有图标 (防止控制台报 icon warning)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}

app.mount('#app')