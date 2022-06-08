import React from "react";
import Header from "./Header.jsx";
import Footer from "./Footer.jsx";
import Jumbotron from "./Jumbotron.jsx";
import Greeting from "./Greeting.jsx";
import Projects from "./Projects.jsx";
import Resume from "./Resume.jsx";
import Skills from "./Skills.jsx";

function App() {
  return (
    <div>
      <Header />
      <Jumbotron />
      <Greeting />
      <Projects />
      <Resume />
      <Skills />
      <Footer />
    </div>
  );
}

export default App;
