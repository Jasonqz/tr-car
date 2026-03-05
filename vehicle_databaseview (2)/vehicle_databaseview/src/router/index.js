import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/', redirect: '/history' },
    {
      path: '/history',
      component: () => import('@/views/HistoricalDataView.vue')
    },
    {
      path: '/realtime',
      component: () => import('@/views/RealTimeDataView.vue')
    }
  ]
})

export default router
