import React, { useState } from "react";
import "../styles/styles.css";
import { InputNumber, Select } from "antd";
import { useNavigate } from "react-router-dom";

const Dashboard= () => {
    const [Xvalue, setX] = useState("");
    const [Yvalue, setY] = useState("");
    const [Rvalue, setR] = useState("");
    const navigate = useNavigate();

    const sendRequest = async () => {
        try {
            const response = await fetch("/addPoint", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                credentials: "include",
                body: JSON.stringify({Xvalue, Yvalue, Rvalue}),
            });


        } catch (error) {
            console.log("An error occurred. Please try again.");
        }
    };

    const handleLogout = () => {

        navigate("/login");
    };

    return (
        <>
            <div className="cap">
                <h1 id="cap">Покалюхин Илья Игоревич</h1>
                <h2>Группа: P3210</h2>
                <p>Вариант: 521992</p>
            </div>
            <div className="dashboard">
                <div className="container">
                    <form id="form">
                        <div className="box-1">
                            <label>Параметр X:</label>
                            <Select
                                defaultValue="0"
                                style={{width: 120}}
                                onChange={(value) => setX(value)}
                                options={[
                                    {value: "-4", label: "-4"},
                                    {value: "-3", label: "-3"},
                                    {value: "-2", label: "-2"},
                                    {value: "-1", label: "-1"},
                                    {value: "0", label: "0"},
                                    {value: "1", label: "1"},
                                    {value: "2", label: "2"},
                                    {value: "3", label: "3"},
                                    {value: "4", label: "4"},
                                ]}
                            />
                        </div>
                        <div className="box-2">
                            <label>Параметр Y:</label>
                            <InputNumber
                                style={{
                                    width: 120,
                                }}
                                defaultValue="0"
                                min={-5}
                                max={5}
                                step={0.01}
                                onChange={(value) => setY(value)}
                                stringMode
                            />
                        </div>
                        <div className="box-3">
                            <label>Параметр R:</label>
                            <Select
                                defaultValue="0"
                                style={{width: 120}}
                                onChange={(value) => setR(value)}
                                options={[
                                    {value: "-4", label: "-4"},
                                    {value: "-3", label: "-3"},
                                    {value: "-2", label: "-2"},
                                    {value: "-1", label: "-1"},
                                    {value: "0", label: "0"},
                                    {value: "1", label: "1"},
                                    {value: "2", label: "2"},
                                    {value: "3", label: "3"},
                                    {value: "4", label: "4"},
                                ]}
                            />
                        </div>
                        <div className={"box-4"}>
                            <button type="submit">Check</button>
                        </div>

                    </form>
                </div>
                <button onClick={handleLogout}>Logout</button>
            </div>
        </>
    );
};

export default Dashboard;
