import { useNavigate } from "react-router-dom";
import "../css/SignUpForm.css";
import Footer from "../Components/Footer";

const SignUpForm = (user) => {
  const navigate = useNavigate();
  const onSubmit = () => {
    navigate("/products");
  };

  const onCancel = () => {
    navigate("/");
  };

  return (
    <div className="formholder">
      <style>
        @import
        url('https://fonts.googleapis.com/css2?family=Raleway:wght@300&display=swap');
      </style>
      <form className="signupform" onSubmit={onSubmit}>
        <h1 className="headerForms">Sign up</h1>
        {user && <input type="hidden" name="_id" defaultValue={user._id} />}

        <div className="control">
          <label htmlFor="name">Username:</label>
          <input
            defaultValue={user ? user.userName : null}
            name="name"
            id="name"
          />
        </div>

        <div className="control">
          <label htmlFor="level">Password:</label>
          <input
            defaultValue={user ? user.passWord : null}
            name="level"
            id="level"
          />
        </div>

        <div className="buttons">
          <button className="button" type="submit">
            Sign up
          </button>
          <button className="button" type="button" onClick={onCancel}>
            Cancel
          </button>
        </div>
      </form>
      <Footer />
    </div>
  );
};

export default SignUpForm;
