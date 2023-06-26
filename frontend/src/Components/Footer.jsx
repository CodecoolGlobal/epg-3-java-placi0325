import logo from "../Images/prelovedlogo.png";
import "../css/Footer.css";

function Footer() {
  return (
    <div className="footer">
      <img src={logo} alt="logo"></img>
      <p className="text">© 2023, PreLoved</p>
    </div>
  );
}

export default Footer;
