import {useState} from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";

export default function AddUser() {

    let navigate = useNavigate()
    const [user, setUser] = useState({
        firstName: "",
        lastName: "",
        email: "",
        password: "",
        city: "",
    });

    const {firstName, lastName, email, password, city} = user;

    const onInputChange = (e) => {
        setUser({...user, [e.target.name]: e.target.value})
    }

    const onSubmit =async (e) => {
        let self = this;
        e.preventDefault();
        await axios.post("http://localhost:3100/user", user)
            .then(function (response) {
                navigate("/about")})
    }

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                    <h2 className="text-center m-4"> Join us! </h2>

                    <form onSubmit={onSubmit}>
                        <div className="mb-3">
                            <label htmlFor="firstName" className="form-label">
                                First name
                            </label>
                            <input
                                type= "firstName"
                                className="form-control"
                                placeholder="Enter your first name"
                                name="name"
                               value = {user.firstName}
                                onChange={handleChange}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="Last name" className="form-label">
                                Last name
                            </label>
                            <input
                                type={"text"}
                                className="form-control"
                                placeholder="Enter your last name"
                                lastName="lastName"
                                defaultValue={user.lastName}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="Email" className="form-label">
                                Email address
                            </label>
                            <input
                                type={"text"}
                                className="form-control"
                                placeholder="Enter your email address"
                                email="email"
                                defaultValue={user.email}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="password" className="form-label">
                                Password
                            </label>
                            <input
                                type = {"text"}
                                className="form-control"
                                placeholder="Enter your password"
                                password = "password"
                                defaultValue={user.password}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="City" className="form-label">
                               City
                            </label>
                            <input
                                type = {"text"}
                                className="form-control"
                                placeholder="Enter your city"
                                city = "city"
                                defaultValue={user.city}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <button type="submit" className="btn btn-outline-primary" >
                            Submit
                        </button>
                    </form>

                </div>
            </div>
        </div>
)
}