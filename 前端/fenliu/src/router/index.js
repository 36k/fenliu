import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

export const constantRoutes = [{
  path: '/login',
  component: () => import('@/views/login/index'),
  hidden: true
},
{
  path: '/',
  component: Layout,
  redirect: '/dashboard',
  children: [{
    path: 'dashboard',
    name: 'Dashboard',
    component: () => import('@/views/dashboard/index'),
    meta: {
      title: '控制台',
      icon: 'dashboard'
    }
  }]
}
]
export const asyncRoutes = [
  {
    path: '/wish',
    component: Layout,
    redirect: '/wish',
    children: [{
      path: 'wish',
      name: 'Wish',
      component: () => import('@/views/wish/index'),
      meta: {
        roles: ['STUDENT'],
        title: '志愿填报',
        icon: 'dashboard'
      }
    }]
  },
  {
    path: '/aspiration',
    component: Layout,
    redirect: '/aspiration',
    children: [{
      path: 'aspiration',
      name: 'aspiration',
      component: () => import('@/views/aspiration/index'),
      meta: {
        roles: ['ADMIN'],
        title: '志愿信息',
        icon: 'dashboard'
      }
    }]
  },
  // {
  //   path: '/database',
  //   component: Layout,
  //   redirect: '/database/address',
  //   name: '数据库管理',
  //   meta: {
  //     title: '数据库管理',
  //     icon: 'example'
  //   },
  //   children: [{
  //     path: 'address',
  //     name: 'Address',
  //     component: () => import('@/views/table/address/index'),
  //     meta: {
  //       title: '地址管理',
  //       icon: 'table'
  //     }
  //   },
  //   {
  //     path: 'province',
  //     name: 'Province',
  //     component: () => import('@/views/table/province/index'),
  //     meta: {
  //       roles: ['studnet'],
  //       title: '省份管理',
  //       icon: 'tree'
  //     }
  //   }
  //   ]
  // },
  // {
  //   path: '/userManage',
  //   component: Layout,
  //   redirect: '/userManage/user',
  //   meta: {
  //     title: '用户管理',
  //     icon: 'table',
  //     roles: ['admin']
  //   },
  //   children: [{
  //     path: 'user',
  //     name: 'User',
  //     component: () => import('@/views/user/index'),
  //     meta: {
  //       title: '用户',
  //       icon: 'tree'
  //     }
  //   }]
  // },
  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  }
]
const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({
    y: 0
  }),
  routes: constantRoutes
})

const router = createRouter()

export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}
export default router
