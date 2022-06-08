import React from "react";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

function Jumbotron(props) {
  return (
    <section id="home">
      <div class="jumbotron jumbotron-fluid">
        <Container>
          <Row>
            <Col>
              <h1 class="jumbo-header">Timothy Michael Jonker</h1>
            </Col>
          </Row>
        </Container>
      </div>
    </section>
  );
}

export default Jumbotron;