import React from "react";
import Container from "react-bootstrap/Container";

const year = new Date().getFullYear();

function Footer(props) {
  return (
    <footer class="footer mt-auto py-3 w-100 bg-dark">
      <Container>
        <p>Copyright &copy; {year}</p>
      </Container>
    </footer>
  );
}

export default Footer;
