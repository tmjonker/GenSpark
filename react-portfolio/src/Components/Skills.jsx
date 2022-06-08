import React from "react";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Picture from "./Picture.jsx";

function Skills(props) {
  return (
    <section id="skills">
      <Container className="section-container">
        <Row>
          <Col className="skills-container">
            <h1 class="section-header">Skills</h1>
            <hr />
            <Col xxl={12} sm={12}>
              <Picture
                class="html"
                source="../images/big-3.png"
                desc="javascript"
              />
            </Col>
            <Col xxl={12} sm={12} className="skill-row">
              <Picture
                class="java"
                source="../images/java.png"
                desc="java"
              />
              <Picture
                class="spring"
                source="../images/spring-boot.png"
                desc="spring-boot"
              />
            </Col>
            <Col xxl={12} sm={12} className="skill-row">
              <Picture
                class="react"
                source="../images/react.png"
                desc="react"
              />
            </Col>
          </Col>
        </Row>
      </Container>
    </section>
  );
}

export default Skills;
