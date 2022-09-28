import './App.css'
import {Login,App1,App2} from './modules'
import {Routes,Route} from 'react-router-dom'
function App() {

  return (
    <Routes>
      <Route path='/login' element={<Login/>}/>
      <Route path='/app1' element={<App1/>}/>
      <Route path='/app2' element={<App2/>}/>
    </Routes>
  )
}

export default App
