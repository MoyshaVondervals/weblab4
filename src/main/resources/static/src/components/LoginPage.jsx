// LoginPage.jsx
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import axios from 'axios';
import { setAuthData } from '../redux/authSlice';
import { Button, Input } from "antd";

const LoginPage = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [message, setMessage] = useState('');

    const dispatch = useDispatch();
    const navigate = useNavigate();

    const handleSubmit = async () => {
        try {
            if (!username || !password) {
                setMessage('Заполните оба поля.');
                return;
            }

            const response = await axios.post('http://localhost:8080/api/auth/login', {
                username,
                password
            });

            dispatch(setAuthData({
                token: response.data.token,
                username: response.data.username
            }));

            navigate('/dashboard');
        } catch (error) {
            console.error('Login failed:', error);
            setMessage('Неправильный логин или пароль.');
        }
    };

    const goReg = () => {
        navigate("/register");
    };

    return (
        <div>
            <h1>Login</h1>
            <Input
                placeholder="Username"
                type="text"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
            />
            <Input.Password
                placeholder="Password"
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
            />
            <Button type="primary" onClick={handleSubmit}>Login</Button>
            <Button type="link" onClick={goReg}>I dont have account</Button>
            <div>{message}</div>
        </div>
    );
};

export default LoginPage;
