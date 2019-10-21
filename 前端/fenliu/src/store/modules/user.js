import { login, logout, getInfo } from '@/api/user'
import { resetRouter } from '@/router'
import { setLogin, removeLogin } from '@/utils/auth'
import { Message } from 'element-ui'
const state = {
  name: '',
  avatar: '',
  roles: []
}

const mutations = {
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  },
  SET_ROLES: (state, role) => {
    state.roles = role
  },
  SET_LOGIN: () => {
    sessionStorage.setItem('login', true)
  },
  RESET_LOGIN: () => {
    sessionStorage.clear()
  },
  RESET_ROLES: state => {
    state.roles = []
  }

}

const actions = {
  // user login
  login({ commit }, userInfo) {
    const { username, password } = userInfo
    return new Promise((resolve, reject) => {
      login({ userId: username.trim(), password: password }).then(response => {
        console.log('sssss')
        setLogin()
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // get user info
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getInfo().then(response => {
        console.log('indo    ')
        const { data } = response
        const { name, role } = data
        const r = []
        r.push(role.roleId)
        commit('SET_NAME', name)
        commit('SET_AVATAR', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif')
        commit('SET_ROLES', r)
        resolve(data)
      }).catch(error => {
        Message({
          message: error,
          type: 'error',
          duration: 5 * 1000
        })
      })
    })
  },

  // user logout
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      logout().then(() => {
        resetRouter()
        removeLogin()
        commit('RESET_ROLES')
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  }

  // remove token
  // resetToken({ commit }) {
  //   return new Promise(resolve => {
  //     resolve()
  //   })
  // }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

