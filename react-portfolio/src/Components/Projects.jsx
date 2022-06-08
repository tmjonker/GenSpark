import React from "react";
import Carousel from "react-bootstrap/Carousel";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Picture from "./Picture";

function Projects(props) {
  return (
    <section id="projects">
      <Container>
        <Row>
          <Col className="section-container">
            <h1 class="section-header">Projects</h1>
            <hr />
            <Carousel className="carousel">
              <Carousel.Item>
                <h4>Password Manager</h4>
                <a href="https://github.com/tmjonker/PasswordManager">
                  <Picture
                    class="d-block w-40 project-img"
                    source="../images/password_manager.png"
                    desc="password-manager"
                  />
                </a>
              </Carousel.Item>
              <Carousel.Item>
                <h4>Pong</h4>
                <a href="https://github.com/tmjonker/pong">
                  <Picture
                    class="d-block w-40 project-img"
                    source="../images/pong.png"
                    desc="pong"
                  />
                </a>
              </Carousel.Item>
              <Carousel.Item>
                <h4>Food2u - a food delivery concept</h4>
                <a href="https://github.com/tmjonker/food2u">
                  <Picture
                    class="d-block w-40 project-img"
                    source="../images/food2u.png"
                    desc="food2u"
                  />
                </a>
              </Carousel.Item>
            </Carousel>
          </Col>
        </Row>
      </Container>
    </section>
  );
}

export default Projects;
