import { Comuna } from "./comuna.model";

export class Persona{
  id?:number;
  nombre?:string;
  apellido?:string;
  correo?:string;
  nacimiento?:Date;
  telefono?:number;
  idComuna?:number;
  comunaDTO?:any;
}
