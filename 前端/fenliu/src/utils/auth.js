
export function getLogin() {
  return sessionStorage.getItem('login')
}
export function setLogin() {
  sessionStorage.setItem('login', true)
}
export function removeLogin() {
  sessionStorage.clear()
}
