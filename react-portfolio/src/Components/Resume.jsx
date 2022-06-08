import {
  VerticalTimeline,
  VerticalTimelineElement,
} from "react-vertical-timeline-component";
import "react-vertical-timeline-component/style.min.css";
import SchoolIcon from "./SchoolIcon.jsx";
import WorkIcon from "./WorkIcon.jsx";
import StarIcon from "./StarIcon.jsx";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

function Resume(props) {
  return (
    <section id="resume">
      <Container className="timeline">
        <Row>
          <Col>
            <h1 class="section-header">Resume</h1>
            <hr />
            <VerticalTimeline>
              <VerticalTimelineElement
                className="vertical-timeline-element--work"
                contentStyle={{
                  borderTop: "2px solid rgb(0, 0, 0)",
                  background: "rgb(255, 255, 255)",
                  color: "#fff",
                }}
                date="2022 - present"
                iconStyle={{ background: "rgb(33, 150, 243)", color: "#fff" }}
                icon={<WorkIcon />}
              >
                <h3 className="vertical-timeline-element-title resume-text">
                  Full Stack Java Developer
                </h3>
                <h4 className="vertical-timeline-element-subtitle resume-text">
                  Manassas, VA
                </h4>
                <p class="resume-text">
                  Full-stack development using Java, React, and Spring-boot
                </p>
              </VerticalTimelineElement>
              <VerticalTimelineElement
                className="vertical-timeline-element--work"
                date="2019 - present"
                iconStyle={{ background: "rgb(33, 150, 243)", color: "#fff" }}
                icon={<WorkIcon />}
              >
                <h3 className="vertical-timeline-element-title resume-text">
                  DoorDash Driver
                </h3>
                <h4 className="vertical-timeline-element-subtitle resume-text">
                  Manassas, VA
                </h4>
                <p class="resume-text">
                  Deliver food to customers who use the DoorDash app
                </p>
              </VerticalTimelineElement>
              <VerticalTimelineElement
                className="vertical-timeline-element--work"
                date="2020 - 2021"
                iconStyle={{ background: "rgb(33, 150, 243)", color: "#fff" }}
                icon={<WorkIcon />}
              >
                <h3 className="vertical-timeline-element-title resume-text">
                  Router
                </h3>
                <h4 className="vertical-timeline-element-subtitle resume-text">
                  Manassas, VA
                </h4>
                <p class="resume-text">
                  Created routes for drivers using Roadnet and Omnitracs XRS
                </p>
              </VerticalTimelineElement>
              <VerticalTimelineElement
                className="vertical-timeline-element--work"
                date="2018 - 2019"
                iconStyle={{ background: "rgb(33, 150, 243)", color: "#fff" }}
                icon={<WorkIcon />}
              >
                <h3 className="vertical-timeline-element-title resume-text">
                  Dispatcher
                </h3>
                <h4 className="vertical-timeline-element-subtitle resume-text">
                  Manassas, VA
                </h4>
                <p class="resume-text">
                  Dispatched a crew of 30 drivers on a nightly basis
                </p>
              </VerticalTimelineElement>
              <VerticalTimelineElement
                className="vertical-timeline-element--education"
                date="2008 - 2017"
                iconStyle={{ background: "rgb(33, 150, 243)", color: "#fff" }}
                icon={<WorkIcon />}
              >
                <h3 className="vertical-timeline-element-title resume-text">
                  Operations Manager II
                </h3>
                <h4 className="vertical-timeline-element-subtitle resume-text">
                  Manassas, VA
                </h4>
                <p class="resume-text">
                  Responsible for managing the outbound operation which
                  consisted of 20 employees
                </p>
              </VerticalTimelineElement>
              <VerticalTimelineElement
                className="vertical-timeline-element--education"
                date="2017 - Present"
                iconStyle={{ background: "rgb(233, 30, 99)", color: "#fff" }}
                icon={<SchoolIcon />}
              >
                <h3 className="vertical-timeline-element-title resume-text">
                  Information Technology
                </h3>
                <h4 className="vertical-timeline-element-subtitle resume-text">
                  A.A.S.
                </h4>
                <p class="resume-text">
                  Still pursuing degree in information technology, estimated
                  graduation date of 12/2023
                </p>
              </VerticalTimelineElement>
              <VerticalTimelineElement
                className="vertical-timeline-element--education"
                date="2017 - 2018"
                iconStyle={{ background: "rgb(233, 30, 99)", color: "#fff" }}
                icon={<SchoolIcon />}
              >
                <h3 className="vertical-timeline-element-title resume-text">
                  Business Information Technology
                </h3>
                <h4 className="vertical-timeline-element-subtitle resume-text">
                  Career Studies Certificate
                </h4>
                <p class="resume-text">Certificate received in 12/2018</p>
              </VerticalTimelineElement>
              <VerticalTimelineElement
                iconStyle={{ background: "rgb(16, 204, 82)", color: "#fff" }}
                icon={<StarIcon />}
              />
            </VerticalTimeline>
          </Col>
        </Row>
      </Container>
    </section>
  );
}

export default Resume;
