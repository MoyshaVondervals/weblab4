import React from "react";
import { useNavigate } from "react-router-dom";
import "../styles/styles.css";

const WelcomePage = () => {
    const navigate = useNavigate();

    return (
        <div className="welcome-page">
            <h1>Welcome to Our Application</h1>
            <p>Choose an option below:</p>
            <button onClick={() => navigate("/login")}>Login</button>
            <button onClick={() => navigate("/register")}>Register</button>
        </div>
    );
};

export default WelcomePage;
