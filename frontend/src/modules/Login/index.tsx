import styles from './styles.module.scss'
export function Login(){
    const login=()=>{
        //TODO
    }
    return (
        <div className={styles.top}>
            <h1>Login Page</h1>
            <input placeholder='username'/>
            <input placeholder='password'/>
            <button className={styles.btn} onClick={login}>login</button>
        </div>
    )
}