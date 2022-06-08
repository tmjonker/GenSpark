import React from "react";
import Navbar from "react-bootstrap/Navbar";
import Nav from "react-bootstrap/Nav";
import Container from "react-bootstrap/Container";

function Header(props) {
  return (
    <header>
      <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark" fixed="top">
        <Container>
          <Navbar.Brand href="#home">Timothy Jonker</Navbar.Brand>
          <Navbar.Toggle />
          <Navbar.Collapse id="responsive-navbar-nav">
            <Nav className="me-auto">
              <Nav.Link href="#about">About</Nav.Link>
              <Nav.Link href="#projects">Projects</Nav.Link>
              <Nav.Link href="#resume">Resume</Nav.Link>
              <Nav.Link href="#skills">Skills</Nav.Link>
            </Nav>
            <Nav className="ml-auto">
              <Nav.Link href="https://www.linkedin.com/in/tmjonker8">
                <i class="fab fa-linkedin fa-lg nav-spacing"></i>
                <span class="sr-only">linkedin</span>
              </Nav.Link>
              <Nav.Link href="https://github.com/tmjonker">
                <i class="fab fa-github fa-lg ml-3"></i>
                <span class="sr-only">github</span>
              </Nav.Link>
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>
    </header>
  );
}

export default Header;
