import { useState, useEffect } from "react";

const Screen = ({ numPokemon, shiny, viewFront }) => {
  const [urlImage, setUrlImage] = useState(
    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
  );

  /*
    Unos cuantos ejemplos de urls
    ------------------------------
    - Pokemon de cara con su color normal:
      https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/6.png
    - Pokemon de espaldas con su color normal:
      https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/6.png
    - Pokemon de cara con su color shiny:
      https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/6.png
    - Pokemon de espaldas con su color shiny:
      https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/shiny/6.png
  */

  /*
    TO-DO: Modificar el useEffect para que se ejecute cada vez que cambie
    numPokemon, shiny o viewFront y cambie la urlImage a la url correspondiente
  */
  useEffect(() => {
    console.log("The pokemon selected has changed");
    let url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon";
    if(viewFront) url += "/back";
    if(shiny) url += "/shiny";
    url += "/" + numPokemon + ".png";
    setUrlImage(url);
  }, [numPokemon, shiny, viewFront]);

  return (
    <div id="screen">
      <img src={urlImage} alt="Imagen de pokemon" />
    </div>
  );
};
export default Screen;
