import {Spinner} from '@chakra-ui/react';
import {useEffect, useState} from 'react';
import {getCustomers} from "./services/client.js";
import UserProfile from "./UserProfile.jsx";

const App = () => {
    const [customers, setCustomers] = useState([]);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        setLoading(true);
        getCustomers().then(res => {
            setCustomers(res.data);
        }).catch(err => {
            console.log(err);
        }).finally(() => {
            setLoading((false));
        })
    }, [])

    if (loading) {
        return (<Spinner
                thickness='4px'
                speed='0.65s'
                emptyColor='gray.200'
                color='blue.500'
                size='xl'
            />)
    }
    if (customers.length <= 0) {
        return (<p>No customers available</p>)
    }

    return (<div>
        {customers.map((user, index) => (
            <UserProfile
                key={index}
                name={user.name}
                age={user.age}
                gender={user.gender}
                imageNumber={index}
            />
        ))}

    </div>)
};


//
// const users = [
//     {
//         name: "Jamila",
//         age: 22,
//         gender: "FEMALE"
//     },
//     {
//         name: "Ana",
//         age: 45,
//         gender: "FEMALE"
//     },
//     {
//         name: "Alex",
//         age: 18,
//         gender: "MALE"
//     },
//     {
//         name: "Bilal",
//         age: 27,
//         gender: "MALE"
//     },
//     {
//         name: "Bob",
//         age: 27,
//         gender: "MALE"
//     }
// ]
//
// const UserProfiles = ({users}) => (
//     <div>
//         {users.map((user, index) => (
//             <UserProfile
//                 key={index}
//                 name={user.name}
//                 age={user.age}
//                 gender={user.gender}
//                 imageNumber={index}
//             />
//         ))}
//     </div>
// )
//
// function App() {
//
//     const [counter, setCounter] = useState(0);
//     const [isLoading, setIsLoading] = useState(false);
//
//     useEffect(() => {
//         setIsLoading(true)
//         setTimeout(() => {
//             setIsLoading(false)
//         }, 4000)
//         return () => {
//             console.log("cleanup functions")
//         }
//     }, [])
//
//     if(isLoading) {
//         return "loading...";
//     }
//
//     return (
//         <div>
//             <button
//                 onClick={() => setCounter(prevCounter => prevCounter + 1)}>
//                 Increment counter
//             </button>
//             <h1>{counter}</h1>
//             <UserProfiles users={users}/>
//         </div>
//     )
// }

export default App


// import { useState } from 'react'
// import reactLogo from './assets/react.svg'
// import viteLogo from '/vite.svg'
// import './App.css'
//
// function App() {
//   const [count, setCount] = useState(0)
//
//   return (
//     <>
//       <div>
//         <a href="https://vitejs.dev" target="_blank">
//           <img src={viteLogo} className="logo" alt="Vite logo" />
//         </a>
//         <a href="https://react.dev" target="_blank">
//           <img src={reactLogo} className="logo react" alt="React logo" />
//         </a>
//       </div>
//       <h1>Vite + React</h1>
//       <div className="card">
//         <button onClick={() => setCount((count) => count + 1)}>
//           count is {count}
//         </button>
//         <p>
//           Edit <code>src/App.jsx</code> and save to test HMR
//         </p>
//       </div>
//       <p className="read-the-docs">
//         Click on the Vite and React logos to learn more
//       </p>
//     </>
//   )
// }
//
// export default App
