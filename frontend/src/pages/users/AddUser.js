import {useState} from "react";
import axios from "axios";

//Zmiana nazwy na Registation lub inny rzeczownik

export default function AddUser() {

    const [user, setUser] = useState({
        firstName: "",
        lastName: "",
        email: "",
        password: "",
        city: "",
    });

    const {firstName, lastName, email, password, city} = user;

    const [firstNameErr, setFirstNameErr] = useState("")


    const onSubmit = (e) => {
        e.preventDefault();
        axios.post("http://localhost:3100/user", user)
            .then((response) => {
                alert("User created!")
                window.location.replace('/profile')
            })
    }


    let name, value;

    const handleInputs = (e) => {
        console.log(e);
        name = e.target.name;
        value = e.target.value;

        setUser({...user, [name]: value});

    }

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                    <h2 className="text-center m-4"> Join us! </h2>

                    <form className="register-form" id="register-form">
                        <div className="mb-3">
                            <label htmlFor="firstName" className="form-label">
                                First name
                            </label>
                            <input
                                type="text"
                                className="form-control"
                                placeholder="Enter your first name"
                                name="firstName"
                                id="firstname"
                                value={user.firstName}
                                onChange={handleInputs}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="lastName" className="form-label">
                                Last name
                            </label>
                            <input
                                type="text"
                                className="form-control"
                                placeholder="Enter your last name"
                                name="lastName"
                                id="lastname"
                                value={user.lastName}
                                onChange={handleInputs}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="email" className="form-label">
                                Email address
                            </label>
                            <input
                                type="text"
                                className="form-control"
                                placeholder="Enter your email address"
                                name="email"
                                id="email"
                                value={user.email}
                                onChange={handleInputs}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="password" className="form-label">
                                Password
                            </label>
                            <input
                                type={"text"}
                                className="form-control"
                                placeholder="Enter your password"
                                name="password"
                                id="password"
                                value={user.password}
                                onChange={handleInputs}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="city" className="form-label">
                                City
                            </label>
                            <input
                                type={"text"}
                                className="form-control"
                                placeholder="Enter your city"
                                name="city"
                                id="city"
                                value={user.city}
                                onChange={handleInputs}
                            />
                        </div>
                        <button type="submit" onClick={onSubmit} className="btn btn-primary">
                            Submit
                        </button>
                        <div className="mt-3">
                            <p className="mb-0  text-center">
                                Already have an account?{' '}
                                <a href="{''}" className="text-primary fw-bold">
                                    Sign In
                                </a>
                            </p>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    )
}