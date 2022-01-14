import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Artifact from '../views/Artifact.vue'
import Contact from '../views/Contact.vue'
import Admin from '../views/Admin.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
    // component: () => import('../views/Home.vue')
  },
  {
    path: '/home',
    name: 'Home',
    component: Home
  },
  {
    path: '/artifact/:type',
    name: 'artifact',
    component: Artifact
  },
  {
    path: '/contact',
    name: 'contact',
    component: Contact
  },
  {
    path: '/admin',
    name: 'admin',
    component: Admin
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
