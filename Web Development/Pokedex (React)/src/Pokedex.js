import { useState, useEffect } from "react";
import Screen from "./Screen";

const Pokedex = () => {
  const [numPokemon, setNumPokemon] = useState(1);
  const [shiny, setShiny] = useState(false);
  const [viewFront, setViewFront] = useState(true);
  const [namePokemon, setNamePokemon] = useState("bulbasaur");

  useEffect(() => {
    fetch("https://pokeapi.co/api/v2/pokemon/" + numPokemon)
      .then((response) => response.json())
      .then((data) => setNamePokemon(data.name));
  }, [numPokemon]);

  const handleClickPrevious = () => {
    /* 
      TO-DO: Reducir en 1 el numero de pokemon
      Si es 1, el anterior será el ultimo pokemon, el 898
    */
    if (numPokemon === 1) setNumPokemon(898);
    else setNumPokemon(numPokemon - 1);
    console.log("Clicked previous");
  };

  const handleClickNext = () => {
    /*
      TO-DO: Aumentar en 1 el numero de pokemon
      Si es el ultimo, el 898, el siguiente será el 1
    */
    if (numPokemon === 898) setNumPokemon(1);
    else setNumPokemon(numPokemon + 1);

    console.log("Clicked next");
  };

  return (
    <div id="pokedex">
      <div>
        <div id="circle1"></div>
        <div id="circle2" className="circleRed"></div>
        <div id="circle2" className="circleYellow"></div>
        <div id="circle2" className="circleGreen"></div>
      </div>
      <div id="screen-row">
        <div id="screen-border">
          <div id="top-screen-decoration">
            <div id="circle3" style={{ marginRight: "20px" }}></div>
            <div id="circle3" style={{ marginLeft: "20px" }}></div>
          </div>
          <Screen numPokemon={numPokemon} shiny={shiny} viewFront={viewFront} />
          <div id="bottom-screen-decoration">
            <div id="circle4"></div>
            <div id="menu-button">
              <svg
                width="22"
                height="16"
                viewBox="0 0 22 16"
                fill="none"
                xmlns="http://www.w3.org/2000/svg"
              >
                <line y1="0.5" x2="22" y2="0.5" stroke="black" />
                <line y1="15.5" x2="22" y2="15.5" stroke="black" />
                <line y1="10.5" x2="22" y2="10.5" stroke="black" />
                <line y1="5.5" x2="22" y2="5.5" stroke="black" />
              </svg>
            </div>
          </div>
        </div>
      </div>
      <div id="name-row">
        <div id="name-container">
          <div id="name-screen">{namePokemon}</div>
          <div id="buttons-container">
            {/*
              TO-DO: Cuando se clicka el botón rojo, la variable shiny debe establecerse a false
            */}
            <div
              id="button-red"
              onClick={() => {
                setShiny(false);
                console.log("Clicked button red");
              }}
            >
              {/*
                TO-DO: Si shiny === false, la luz deberá estar encendida, para ello
                 se debe añadir al div la clase on. Si por el contrario shiny === true,
                 la luz estará apagada, por lo que se añadirá la clase off
              */}
              {!shiny ? (<div className="light on"></div>) : (<div className="light off"></div>)}
            </div>
            {/*
              TO-DO: Cuando se clicka el botón azul, la variable shiny debe establecerse a true
            */}
            <div
              id="button-blue"
              onClick={() => {
                setShiny(true);
                console.log("Clicked button blue");
              }}
            >
              {/*
                TO-DO: Si shiny === true, la luz deberá estar encendida, para ello
                 se debe añadir al div la clase on. Si por el contrario shiny === false,
                 la luz estará apagada, por lo que se añadirá la clase off
              */}
              {shiny ? (<div className="light on"></div>) : (<div className="light off"></div>)}
            </div>
          </div>
          <div id="arrows-container">
            <div id="cross">
              {/*
                TO-DO: Al pulsar el boton izquierdo o derecho, la variable viewFront 
                debe establecerse al valor contrario del actual
              */}
              <div
                id="leftcross"
                onClick={() => {
                  setViewFront(!viewFront);
                  console.log("Clicked left");
                }}
              >
                <div id="leftT"></div>
              </div>
              <div id="topcross" onClick={() => handleClickPrevious()}>
                <div id="upT"></div>
              </div>
              {/*
                TO-DO: Al pulsar el boton izquierdo o derecho, la variable viewFront 
                debe establecerse al valor contrario del actual
              */}
              <div
                id="rightcross"
                onClick={() => {
                  setViewFront(!viewFront);
                  console.log("Clicked right");
                }}
              >
                <div id="rightT"></div>
              </div>
              <div id="midcross">
                <div id="midCircle"></div>
              </div>
              <div id="botcross" onClick={() => handleClickNext()}>
                <div id="downT"></div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Pokedex;
