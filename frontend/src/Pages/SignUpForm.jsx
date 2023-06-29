import { useNavigate } from "react-router-dom";
import "../css/SignUpForm.css";
import Footer from "../Components/Footer";
import {useState} from "react";
import Loading from "./Loading/Loading";


const createNewUser = (user) => {
  return fetch("/clients", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(user),
  });
};

const SignUpForm = (user) => {
  const navigate = useNavigate();
  const [loading, setLoading] = useState();

  const onSubmit = (e) => {
      e.preventDefault();
      const formData = new FormData(e.target);
      const user = {};

      for (let [key, value] of formData.entries()) {
          user[key] = value;
      }
      handleCreateNewUser(user);
  };

  const handleCreateNewUser = (user) => {
    setLoading(true);

    createNewUser(user)
        .then(() => {
          navigate("/");
        })
        .catch((err) => {
          throw err;
        })
        .finally(() => {
          setLoading(false);
        });
  };

  const onCancel = () => {
    navigate("/");
  };

  if(loading) {
    return < Loading />;
  }

  return (
    <div className="formholder">
      <style>
        @import
        url('https://fonts.googleapis.com/css2?family=Raleway:wght@300&display=swap');
      </style>
      <form className="signupform" onSubmit={onSubmit}>
        <h1 className="headerForms">Sign up</h1>
         <input type="hidden" name="_id" defaultValue={user._id} />

        <div className="control">
          <label htmlFor="clientName">Username:</label>
          <input
            name="clientName"
            id="clientName"
            type="text"
          />
        </div>

        <div className="control">
          <label htmlFor="password">Password:</label>
          <input
            name="password"
            id="password"
            type="password"
          />
        </div>

        <div className="buttons">
          <button className="button" type="submit">
            Sign up
          </button>
          <button className="button" type="button" onClick={onCancel
}>
            Cancel
          </button>
        </div>
      </form>
      <Footer />
    </div>
  );
};

export default SignUpForm;