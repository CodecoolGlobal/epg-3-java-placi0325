import { useNavigate } from "react-router-dom";
import Footer from "../Components/Footer";

const LoginForm = (user) => {
  const navigate = useNavigate();
  const onSubmit = () => {
    try{navigate("/");}
    catch{return <h1>Your username or password is incorrect, please try again!</h1>}
  };

  const onCancel = () => {
    navigate("/")
  };

  return (
    <div className="formholder">
      <style>
        @import
        url('https://fonts.googleapis.com/css2?family=Raleway:wght@300&display=swap');
      </style>
    <form className="signupform" onSubmit={onSubmit}>
      <h1 className="headerForms">Log in</h1>
      {user && <input type="hidden" name="_id" defaultValue={user._id} />}

      <div className="control">
        <label htmlFor="clientName">Username:</label>
        <input
          name="clientName"
          id="clientName"
        />
      </div>

      <div className="control">
        <label htmlFor="password">Password:</label>
        <input
          name="password"
          id="password"
          type={"password" }
        />
      </div>

      <div className="buttons">
        <button className="button" type="submit">Login </button>
        <button className="button" type="button" onClick={onCancel}>
          Cancel
        </button>
      </div>
    </form>
    <Footer/>
    </div>
  );
};

export default LoginForm;
