import './App.css'
import { Route, BrowserRouter as Router, Routes } from 'react-router-dom'
import Navbar from './components/NavBar';
import LandingPage from './components/LandingPage';
import AboutPage from './components/AboutPage';
import ShortenUrlPage from './components/ShortenUrlPage';
import PrivateRoute from './PrivateRoute';
import RegisterPage from './components/RegisterPage';
import LoginPage from './components/LoginPage';
import DashboardLayout from './components/Dashboard/DashboardLayout';
import ErrorPage from './components/ErrorPage';
import Footer from './components/Footer';
import { Toaster } from 'react-hot-toast';

function App() {
  const hideHeaderFooter = location.pathname.startsWith("/s");

  return (
    <Router>
      {!hideHeaderFooter && <Navbar /> }
        <Toaster position='bottom-center'/>
        <Routes>
          <Route path="/" element={<LandingPage />} />
          <Route path="/about" element={<AboutPage />} />
          <Route path="/s/:url" element={<ShortenUrlPage />} />

          <Route path="/register" element={<PrivateRoute publicPage={true}><RegisterPage /></PrivateRoute>} />
          <Route path="/login" element={<PrivateRoute publicPage={true}><LoginPage /></PrivateRoute>} />
          
           <Route path="/dashboard" element={ <PrivateRoute publicPage={false}><DashboardLayout /></PrivateRoute>} />
          <Route path="/error" element={ <ErrorPage />} />
          <Route path="*" element={ <ErrorPage message="We can't seem to find the page you're looking for"/>} />
        </Routes>
        {!hideHeaderFooter && <Footer />}
    </Router>
  )
}

export default App
