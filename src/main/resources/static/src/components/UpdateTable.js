import { Table } from "antd";
import React, { useEffect, useState, forwardRef, useImperativeHandle } from "react";
import "../styles/styles.css";
import { useNavigate } from "react-router-dom";
import { useAtom } from "jotai";
import { jwtTokenAtom } from "../redux/store";
import axios from "axios";

const TableDots = forwardRef((props, ref) => {
    const [jwtToken] = useAtom(jwtTokenAtom);
    const [dataResp, setDataResp] = useState([]);
    const navigate = useNavigate();

    const fetchData = async () => {
        try {
            const response = await axios.get("http://localhost:8080/api/dot/get10", {
                headers: {
                    Authorization: "Bearer " + jwtToken,
                },
            });
            setDataResp(response.data);
        } catch (error) {
            console.error("Ошибка при загрузке данных:", error);
            if (error.response?.status === 401) {
                navigate("/login");
            }
        }
    };

    useEffect(() => {
        fetchData();
    }, [jwtToken, navigate]);

    useImperativeHandle(ref, () => ({
        refreshTable: fetchData,
    }));

    const columns = [
        {
            title: "X",
            dataIndex: "x",
            key: "x",
        },
        {
            title: "Y",
            dataIndex: "y",
            key: "y",
        },
        {
            title: "R",
            dataIndex: "r",
            key: "r",
        },
        {
            title: "Status",
            dataIndex: "isHit",
            key: "status",
            render: (isHit) => (
                <span style={{ color: isHit ? "green" : "red" }}>
                    {isHit ? "Hit" : "Miss"}
                </span>
            ),
        },
    ];

    return (
        <div className="table-container">
            <Table
                dataSource={dataResp}
                columns={columns}
                rowKey={(record) => record.id}
            />
        </div>
    );
});

export default TableDots;
