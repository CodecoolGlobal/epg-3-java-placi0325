import logo from "../Images/prelovedlogo.png";
import twitterLogo from "../Images/twitter.svg"
import facebookLogo from "../Images/facebook.svg"
import instagramLogo from "../Images/instagram.svg"
import { useNavigate } from "react-router-dom";
import  "../css/Header.css";

const Header = () => {
  
    const navigate = useNavigate();

    return (
      <header className="header">
        <div className="headerDiv">
          <div className='logoContainer'>
              <img className="picture" src={logo} alt='logo'></img>
            
              <img className="picture" src={twitterLogo} alt='twitter logo'></img>
           
            
              <img className="picture" src={facebookLogo} alt='facebook logo'></img>
           
           
              <img className="picture" src={instagramLogo} alt='instagram logo'></img>
           
          </div>
          <div className='button-containers'>
              <button className='button' onClick={() => navigate("/newproduct")}>Add new product</button>
          </div>
        </div>
      </header>
    )
  }
  
  export default Header;