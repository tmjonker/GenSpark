import React from "react";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Picture from "./Picture.jsx";
import Paragraph from "./Paragraph.jsx";

function Greeting(props) {
  return (
    <Container>
      <Row>
        <Col className="w-60 d-block">
          <section id="about">
            <Container>
              <Row>
                <Col className="section-container">
                  <Picture
                    class="hello"
                    source="../images/hello.png"
                    desc="timothy jonker"
                  />
                  <hr />
                  <Paragraph
                    class="about-me first-paragraph"
                    text="I've been working in the transportation industry for the greater
                  part of 12 years in a multitude of roles. I've been a warehouse worker, administrative associate,
                  route coordinator, and manager. While I love and adore the industry and those I've shared a workspace
                  with,   I've made a decision to pivot toward a career that is closer to my heart: software development."
                  />
                  <Paragraph
                    class="about-me"
                    text="I've been working exhaustively toward getting myself ready to
                  enter the workforce as a software developer by taking classes on web design, java development,
                  spring boot, android development, and SQL/database management. I have been working on several personal projects that
                  showcase my skills in these areas and you can view the source code for these projects by visiting my github."
                  />
                </Col>
              </Row>
            </Container>
          </section>
        </Col>
      </Row>
    </Container>
  );
}

export default Greeting;
