package tp.caperucita.search;

import tp.caperucita.search.auxiliar.ContenidoCelda;
import tp.caperucita.search.auxiliar.PosicionCelda;

public class Configuracion {

    public static ContenidoCelda[][] mapaAmbiente = {
            {ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO},
            {ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE    , ContenidoCelda.LIBRE    ,ContenidoCelda.LIBRE    , ContenidoCelda.LIBRE    ,ContenidoCelda.LIBRE    , ContenidoCelda.LIBRE    , ContenidoCelda.LIBRE    ,ContenidoCelda.LIBRE    ,ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO},
            {ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE    , ContenidoCelda.OBSTACULO,ContenidoCelda.LIBRE    , ContenidoCelda.LIBRE    ,ContenidoCelda.LIBRE    , ContenidoCelda.LIBRE    , ContenidoCelda.LIBRE    ,ContenidoCelda.LIBRE    ,ContenidoCelda.LIBRE    , ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO},
            {ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE    , ContenidoCelda.LIBRE    ,ContenidoCelda.LIBRE    , ContenidoCelda.LIBRE    ,ContenidoCelda.LIBRE    , ContenidoCelda.LIBRE    , ContenidoCelda.OBSTACULO,ContenidoCelda.LIBRE    ,ContenidoCelda.LIBRE    , ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO},
            {ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO,ContenidoCelda.LIBRE    , ContenidoCelda.LIBRE    ,ContenidoCelda.LIBRE    , ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE    ,ContenidoCelda.LIBRE    ,ContenidoCelda.LIBRE    , ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO},
            {ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE    , ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE    ,ContenidoCelda.LIBRE    , ContenidoCelda.LIBRE    , ContenidoCelda.LIBRE    ,ContenidoCelda.LIBRE    ,ContenidoCelda.LIBRE    , ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO},
            {ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE    , ContenidoCelda.LIBRE    ,ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE    , ContenidoCelda.OBSTACULO,ContenidoCelda.LIBRE    ,ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO},
            {ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.LIBRE    , ContenidoCelda.LIBRE    ,ContenidoCelda.LIBRE    , ContenidoCelda.OBSTACULO,ContenidoCelda.LIBRE    , ContenidoCelda.LIBRE    , ContenidoCelda.LIBRE    ,ContenidoCelda.LIBRE    ,ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO},
            {ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO,ContenidoCelda.LIBRE    , ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO, ContenidoCelda.OBSTACULO,ContenidoCelda.OBSTACULO}
    };

    public static int filaInicialCaperucita = 5;
    public static int columnaInicialCaperucita = 11;

    public static int filaFlores = 8;
    public static int columnaFlores = 7;

}
