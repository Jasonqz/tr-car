import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import pxtorem from 'postcss-pxtorem'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  assetsInclude: ['**/*.hdr'], // 处理.hdr文件
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  css: {
    postcss: {
      plugins: [
        pxtorem({
          rootValue: 80, // 按照自己的设计稿修改 1920/24  针对设计稿的，最后大小显示还是按照屏幕来定
          unitPrecision: 5, // 保留到5位小数
          selectorBlackList: ['ignore'], // 忽略转换正则匹配项
          propList: ['*', '!border'],
          replace: true,
          mediaQuery: false, // 媒体查询( @media screen 之类的)中不生效
          minPixelValue: 1 //可以选择px小于1的不会被转换
        })
      ]
    }
  }
})
