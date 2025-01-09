export function addPointToGraph(svgElement, points) {
    const pointsGroup = svgElement.querySelector("#pointsGroup");


    points.forEach((point) => {
        const cx = 180 + point.x * 30;
        const cy = 180 - point.y * 30;

        const circle = document.createElementNS("http://www.w3.org/2000/svg", "circle");
        circle.setAttribute("cx", cx);
        circle.setAttribute("cy", cy);
        circle.setAttribute("r", 4);
        circle.setAttribute("fill", point.status ? "green" : "red");

        pointsGroup.appendChild(circle);
    });
}
