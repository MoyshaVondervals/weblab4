import React, {useRef, useState} from "react";
import { useNavigate } from "react-router-dom";
import "../styles/styles.css";
import axios from "axios";
import {useSetAtom} from "jotai/index";
import {globalUsernameAtom, jwtTokenAtom} from "../redux/store";

const RegisterPage = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const passwordRef = useRef(null);
    const setJwtToken = useSetAtom(jwtTokenAtom);
    const setGlobalUsername = useSetAtom(globalUsernameAtom)
    const navigate = useNavigate();

    const handleRegister = async () => {
        try {
            if (!username || !password) {
                setError('Please enter both username and password.');
                return;
            }

            const response = await axios.post('http://localhost:8080/api/auth/register', {
                "username": username,
                "password": password
            });
            setJwtToken(response.data.token);
            setGlobalUsername(response.data.username);
            navigate('/dashboard');
        } catch (error) {
            console.error('Login failed:');
            setError('Invalid username or password.');
        }
    };

    return (
        <>
    <div className="register-page">
        <h1>Register</h1>
        <form onSubmit={handleRegister}>
            <div>
                <label>Username:</label>
                    <input
                        type="text"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label>Password:</label>
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>
                <button type="submit">Register</button>

            </form>
            <button onClick={() => navigate("/login")}>Go to login</button>
            <button onClick={() => navigate("/")}>Back</button>
        </div>
            </>
    );
};

export default RegisterPage;


