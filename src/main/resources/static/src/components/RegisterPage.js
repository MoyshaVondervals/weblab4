import React, {useRef, useState} from "react";
import { useNavigate } from "react-router-dom";
import "../styles/styles.css";
import axios from "axios";
import {useSetAtom} from "jotai/index";
import {globalUsernameAtom, jwtTokenAtom} from "../redux/store";
import {Button, Input} from "antd";

const RegisterPage = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [message, setMessage] = useState('');
    const setJwtToken = useSetAtom(jwtTokenAtom);
    const setGlobalUsername = useSetAtom(globalUsernameAtom)
    const navigate = useNavigate();

    const handleRegister = async () => {
        try {
            if (!username || !password) {
                setMessage('Заполните оба поля');
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
            setMessage('Не правильные логин или пароль');
        }
    };
    const goLog = () =>{
        navigate("/");
    }

    return (
        <>
            <div className="register-page">
                <h1>Register</h1>

                <Input placeholder="Username"
                       type="text"
                       value={username}
                       onChange={(e) => setUsername(e.target.value)}
                />
                <Input.Password placeholder="Password"
                                type="password"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                />
                <Button type="primary" onClick={handleRegister}>Register</Button>
                <Button type="link" onClick={goLog}>Go to login</Button>
                <div>
                    {message}
                </div>
            </div>
        </>
    );
};

export default RegisterPage;


