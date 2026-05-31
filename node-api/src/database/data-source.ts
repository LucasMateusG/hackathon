import "reflect-metadata";
import "dotenv/config";
import { DataSource } from "typeorm";
import path from "path";
import  { UserEntity }  from "../models/UserEntity.js";

const databasePath = path.join(process.cwd(), "database", "app.db");
const migrationsPath = path.resolve(__dirname, './migrations/*.ts');
export const AppDataSource = new DataSource({
    type: "mysql",
    database: databasePath,
    entities: [UserEntity],
    synchronize: true,
    logging: false,
    migrations: [migrationsPath],
    migrationsRun: true,
});