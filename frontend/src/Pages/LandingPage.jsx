import "../css/LandingPage.css";
import logo from "../Images/prelovedlogo.png";
import { Link } from "react-router-dom";
import Footer from "../Components/Footer.jsx"

const LandingPage = () => {
  return (
    <div className="landingPage">
      <style>
        @import
        url('https://fonts.googleapis.com/css2?family=Raleway:wght@300&display=swap');
      </style>
      <img src={logo} alt="logo"className="image"></img>
      <h1 className="welcometext">Welcome to PreLoved!</h1>
      <div className="buttonContainer">
      <Link to={"/signup"}>
        <button type="button" className="landingButton">Sign up</button>
      </Link>
      <Link to={"/login"}>
        <button type="button" className="landingButton">Login</button>
      </Link>
      </div>
      <Footer />
    </div>
  );
};

export default LandingPage;
