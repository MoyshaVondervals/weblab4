import React, { forwardRef } from "react";

const DrawGraph = forwardRef(({ Rvalue, onClick }, ref) => {
    return (
        <svg
            ref={ref}
            xmlns="http://www.w3.org/2000/svg"
            width="360"
            height="360"
            id="svgId"
            onClick={onClick}
        >
            <rect
                x={180 - 30 * Rvalue}
                y="180"
                width={30 * Rvalue}
                height={30 * Rvalue}
                className="geometryShape"
                id="rect"
            />
            <polygon
                points={`${180},${180 + 15 * Rvalue} ${180},${180} ${180 + 30 * Rvalue},${180}`}
                className="geometryShape"
                id="tri"
            />
            <path
                d={`M${180 - 30 * Rvalue},${180} A${30 * Rvalue},${30 * Rvalue} 0 0,1 ${180},${
                    180 - 30 * Rvalue
                } L${180},${180} Z`}
                className="geometryShape"
                id="circle"
            />

            {/* X axis */}
            <line stroke="black" x1="0" x2="360" y1="180" y2="180"/>
            <line stroke="black" x1="350" x2="360" y1="175" y2="180"/>
            <line stroke="black" x1="350" x2="360" y1="185" y2="180"/>
            <text fill="black" x="348" y="170">
                X
            </text>

            {/* Y axis */}
            <line stroke="black" x1="180" x2="180" y1="0" y2="360"/>
            <line stroke="black" x1="180" x2="185" y1="0" y2="10"/>
            <line stroke="black" x1="180" x2="175" y1="0" y2="10"/>
            <text fill="black" x="190" y="12">
                Y
            </text>

            {/* X points */}
            <line stroke="black" x1={180 - 30 * Rvalue} x2={180 - 30 * Rvalue} y1="183" y2="177"/>
            <line stroke="black" x1={180 - 15 * Rvalue} x2={180 - 15 * Rvalue} y1="183" y2="177"/>
            <line stroke="black" x1={180 + 15 * Rvalue} x2={180 + 15 * Rvalue} y1="183" y2="177"/>
            <line stroke="black" x1={180 + 30 * Rvalue} x2={180 + 30 * Rvalue} y1="183" y2="177"/>

            {/* X points R */}
            <text fill="black" x={180 - 30 * Rvalue} y="170" fontSize="8">
                -R
            </text>
            <text fill="black" x={180 - 15 * Rvalue} y="170" fontSize="8">
                -R/2
            </text>
            <text fill="black" x={180 + 15 * Rvalue} y="170" fontSize="8">
                R/2
            </text>
            <text fill="black" x={180 + 30 * Rvalue} y="170" fontSize="8">
                R
            </text>

            {/* Y points */}
            <line stroke="black" x1="177" x2="183" y1={180 - 30 * Rvalue} y2={180 - 30 * Rvalue} id="YlR"/>
            <line stroke="black" x1="177" x2="183" y1={180 - 15 * Rvalue} y2={180 - 15 * Rvalue} id="YlR2"/>
            <line stroke="black" x1="177" x2="183" y1={180 + 15 * Rvalue} y2={180 + 15 * Rvalue} id="YlmR2"/>
            <line stroke="black" x1="177" x2="183" y1={180 + 30 * Rvalue} y2={180 + 30 * Rvalue} id="YlmR"/>

            {/* Y points R */}
            <text fill="black" x="190" y={180 - 30 * Rvalue} fontSize="8">
                R
            </text>
            <text fill="black" x="190" y={180 - 15 * Rvalue} fontSize="8">
                R/2
            </text>
            <text fill="black" x="190" y={180 + 15 * Rvalue} fontSize="8">
                -R/2
            </text>
            <text fill="black" x="190" y={180 + 30 * Rvalue} fontSize="8">
                -R
            </text>
            <g id="pointsGroup"></g>
        </svg>



    );
});

export default DrawGraph;
