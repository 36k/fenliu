const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  roles: state => state.user.roles,
  login: () => sessionStorage.getItem('login'),
  permission_routes: state => state.permission.routes
}
export default getters
