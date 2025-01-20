// DashBoard.jsx
import React, { useRef, useState, useEffect } from "react";
import "../styles/styles.css";
import { Button, InputNumber, Select } from "antd";
import { useNavigate } from "react-router-dom";
import DrawGraph from './Graph';
import { addPointToGraph } from "./AddDot";
import TableDots from "./UpdateTable";
import axios from 'axios';

// Redux
import { useSelector, useDispatch } from "react-redux";
import { clearAuthData } from "../redux/authSlice";

const Dashboard = () => {
    const [Xvalue, setX] = useState("");
    const [Yvalue, setY] = useState("");
    const [Rvalue, setR] = useState("1");
    const token = useSelector((state) => state.auth.token);
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const [message, setMessage] = useState('');
    const svgRef = useRef(null);
    const tableRef = useRef(null);

    useEffect(() => {
        if (svgRef.current) {
            restorePointsOnGraph();
        }
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [token]);

    const restorePointsOnGraph = async () => {
        try {
            const response = await axios.get("http://localhost:8080/api/dot/get10", {
                headers: {
                    Authorization: "Bearer " + token,
                },
            });
            const points = response.data;
            points.forEach((point) => {
                setNewDot(point);
            });
        } catch (error) {
            console.error(error);
        }
    };

    const handleLogout = () => {
        dispatch(clearAuthData());
        navigate('/');
    };

    const handleGraphClick = async (event) => {
        if (!svgRef.current) return;
        const rect = svgRef.current.getBoundingClientRect();

        const clickX = event.clientX - rect.left;
        const clickY = event.clientY - rect.top;

        const graphX = ((clickX - 180) / 30).toFixed(2);
        const graphY = ((180 - clickY) / 30).toFixed(2);

        try {
            const response = await axios.post('http://localhost:8080/api/dot',
                {
                    x: graphX,
                    y: graphY,
                    r: Rvalue,
                },
                {
                    headers: {
                        Authorization: "Bearer " + token
                    },
                }
            );
            setMessage("Точка успешно добавлена!");
            setNewDot(response.data);
            await tableRef.current.refreshTable();
        } catch (error) {
            console.error(error);
            setMessage("Ошибка при добавлении точки");
        }
    };

    const setNewDot = (data) => {
        addPointToGraph(svgRef.current, [{
            x: data.x,
            y: data.y,
            r: data.r,
            status: data.isHit,
        }]);
    };

    const handleSubmit = async () => {
        try {
            const response = await axios.post('http://localhost:8080/api/dot',
                {
                    x: Xvalue,
                    y: Yvalue,
                    r: Rvalue
                },
                {
                    headers: {
                        Authorization: "Bearer " + token
                    },
                }
            );
            setMessage("Точка успешно добавлена!");
            setNewDot(response.data);
            await tableRef.current.refreshTable();
        } catch (error) {
            console.error(error);
            setMessage("Ошибка при добавлении точки");
        }
    };

    return (
        <>
            <div className="dashboard">
                <div className="container">
                    <div className={"box-1"} style={{ width: 140 }}>
                        <label>Параметр X:</label>
                        <Select
                            defaultValue="0"
                            style={{ width: 120 }}
                            onChange={(value) => setX(value)}
                            options={[
                                { value: "-4", label: "-4" },
                                { value: "-3", label: "-3" },
                                { value: "-2", label: "-2" },
                                { value: "-1", label: "-1" },
                                { value: "0", label: "0" },
                                { value: "1", label: "1" },
                                { value: "2", label: "2" },
                                { value: "3", label: "3" },
                                { value: "4", label: "4" },
                            ]}
                        />
                    </div>
                    <div className={"box-2"} style={{ width: 140 }}>
                        <label>Параметр Y:</label>
                        <InputNumber
                            style={{ width: 120 }}
                            defaultValue="0"
                            min={-5}
                            max={5}
                            step={0.01}
                            onChange={(value) => setY(value)}
                            stringMode
                        />
                    </div>
                    <div className={"box-3"} style={{ width: 140 }}>
                        <label>Параметр R:</label>
                        <Select
                            defaultValue="1"
                            style={{ width: 120 }}
                            onChange={(value) => setR(value)}
                            options={[
                                { value: "1", label: "1" },
                                { value: "2", label: "2" },
                                { value: "3", label: "3" },
                                { value: "4", label: "4" },
                            ]}
                        />
                    </div>
                    <div className={"box-4"}>
                        <DrawGraph Rvalue={Rvalue} ref={svgRef} onClick={handleGraphClick} />
                    </div>
                    <div className={"box-5"}>
                        <Button type="primary" onClick={handleSubmit}>Проверить</Button>
                        {message}
                    </div>
                    <div className={"box-6"}>
                        <TableDots ref={tableRef} />
                    </div>
                </div>
            </div>
            <Button type="primary" onClick={handleLogout} danger>Выйти</Button>
        </>
    );
};

export default Dashboard;
