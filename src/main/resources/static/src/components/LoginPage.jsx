import { useRef, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { useSetAtom } from 'jotai';
import { globalUsernameAtom, jwtTokenAtom } from '../redux/store';
import {Button, Input} from "antd";

const LoginPage= () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [message, setMessage] = useState('');
    const setJwtToken = useSetAtom(jwtTokenAtom);
    const setGlobalUsername = useSetAtom(globalUsernameAtom)
    const navigate = useNavigate();
    function handleLogout() {
        setJwtToken(null);
        setGlobalUsername(null);
        navigate('/');
    };


    const handleSubmit = async () => {
        try {
            if (!username || !password) {
                setMessage('Please enter both username and password.');
                return;
            }

            const response = await axios.post('http://localhost:8080/api/auth/login', {
                "username": username,
                "password": password
            });
            setJwtToken(response.data.token);
            setGlobalUsername(response.data.username);
            navigate('/dashboard');
        } catch (error) {
            console.error('Login failed:');
            setMessage('Invalid username or password.');
        }
    };
    const goReg = () =>{
        navigate("/register");
    }

    return (
        <div>
            <h1>Login</h1>

                <Input placeholder="Username"
                type = "text"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                />
                <Input.Password placeholder="Password"
                type = "password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                />
            <Button type="primary" onClick={handleSubmit}>Login</Button>
            <Button type="link" onClick={goReg}>I dont have account</Button>
            <div>
                {message}
            </div>
            <Button type="primary" onClick={handleLogout}>logout</Button>

        </div>
    );
}

export default LoginPage;
