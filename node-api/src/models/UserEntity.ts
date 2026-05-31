import { Column, 
    Entity, 
    PrimaryGeneratedColumn, 
} from "typeorm";

@Entity({name : "User"})
export class UserEntity { 
    @PrimaryGeneratedColumn()
    id!: number;

    @Column({ type: "text"})
    name!: string;

    @Column({ type: "text", unique: true})
    email!: string;

    @Column({ type: "text"})
    password!: string;

}

export type PublicUser = Omit<UserEntity, "password">;