import {useNavigate} from "react-router-dom";
import Footer from "../Components/Footer";
import {useState} from "react";
import Loading from "./Loading/Loading";


const LoginForm = (user) => {
    const [loading, setLoading] = useState();
    const navigate = useNavigate();

    const loginUser = (user) => {
        try {
            return fetch("/login", {
                method: "POST",
                headers: {

                    "Content-Type": "application/json",
                },
                body: JSON.stringify(user),
            });
        } catch {
            navigate("/")
            console.log("invalid username and/or password")
            return <h1>invalid username and/or password</h1>
        }
    }

    const onSubmit = (e) => {
        e.preventDefault();
        const formData = new FormData(e.target);
        const userdata = {};

        for (let [key, value] of formData.entries()) {
            userdata[key] = value;
        }
        handleLoginUser(userdata);
    };

    const onCancel = () => {
        navigate("/")
    };

    const handleLoginUser = (user) => {
        setLoading(true);
        loginUser(user)
            .then(() => {
                navigate("/");
            })
            .catch(() => {
                console.log("invalid username or password");
            })
            .finally(() => {
                setLoading(false);
            });
    }

    if (loading) {
        return <Loading/>
    }

    return (
        <div className="formholder">
            <style>
                @import
                url('https://fonts.googleapis.com/css2?family=Raleway:wght@300&display=swap');
            </style>
            <form className="signupform" onSubmit={onSubmit}>
                <h1 className="headerForms">Log in</h1>
                <div className="control">
                    <label htmlFor="username">Username:</label>
                    <input
                        name="username"
                        id="username"
                    />
                </div>

                <div className="control">
                    <label htmlFor="password">Password:</label>
                    <input
                        name="password"
                        id="password"
                        type={"password"}
                    />
                </div>

                <div className="buttons">
                    <button className="button" type="submit">Login</button>
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
