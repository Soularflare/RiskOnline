import { Link } from "react-router-dom";
import { useEffect, useState } from "react/cjs/react.development";
import { addAvatar, equipAvatar, updatePoints } from "./apiServices/microtransactionApi";
import { fetchPts, fetchUserInfo } from "./apiServices/userApi";

function Avatars({ userData }) {
    const TOTAL_AVATARS = 9;
    const [userPoints, setUserPoints] = useState(0);
    const [equipped, setEquipped] = useState(null);
    const [avatarList, setAvatarList] = useState([{owned: false, equipped: false, microtransaction: {}, cost: 900}]);
    const [userId, setUserId] = useState(0);
    const [selected, setSelected] = useState(0);

    useEffect(() => {
        fetchUserInfo(userData)
            .then((userInfo) => {
                console.log(userInfo);
                const avatars = [];
                let j = 0;
                console.log("LOOK HERE");
                console.log(userInfo.microtransactions);
                for (let i = 1; i <= TOTAL_AVATARS; i++) {
             
                        avatars.push({
                            owned: false,
                            equipped: false,
                            microtransaction: { id: i },
                            cost: i * 100
                        })
                    
                    }

                for(let x = 0;x<userInfo.microtransactions.length;x++){
                    for(let y = 0; y< avatars.length; y++)
                    {
                        console.log("Whats up doc?");
                        console.log(avatars);
                        if(avatars[y].microtransaction.id == userInfo.microtransactions[x].microtransaction.id){
                            
                            //avatars.push({ owned: true, ...userInfo.microtransactions[x] });
                            avatars[y] = { owned: true, equipped: userInfo.microtransactions[x].equipped, ...userInfo.microtransactions[x] };

                        }
                    }
                }
                    // if (userInfo.microtransactions.length > j && i == userInfo.microtransactions[j].microtransaction.id) {
                    //     avatars.push({ owned: true, ...userInfo.microtransactions[j] });
                    //     j++;
                    // }

                    setUserId(userInfo.userId);
                    setUserPoints(userInfo.points);
                    console.log("Avatars:");
                    console.log(avatars);
                    setAvatarList([...avatars]);
                    for (let i = 0; i < avatars.length; i++) {
                        if (avatars[i].equipped == true) {
                            setEquipped(i);
                        }
                    }
                    // setAvatarList([...avatars]); 
                
            })
            .catch((err) => console.log(err.toString()));

        // console.log(avatarList);
    }, []);

    useEffect(() => {
        // console.log(avatarList);
        if (avatarList.length == TOTAL_AVATARS) {
            for (let i = 0; i < avatarList.length; i++) {
                console.log("AvatarList");
                console.log(avatarList);
                if (avatarList[i].owned) {
                    document.getElementById(i + 1).style.opacity = "1.0";
                }
                if (avatarList[i].equipped == true) {
                    setEquipped(i);
                }
            }
        }
    }, [avatarList]);

    const optionselect = evt => {

        const id = evt.target.id;
        setSelected(id);
        const img = document.getElementById(id);
        const images = document.getElementsByTagName("input");
        const cost = document.getElementById("cost");

        for (let i = 0; i < images.length; i++) {
            const element = images.item(i);
            if (element.id !== evt.target.id) {
                element.style.borderStyle = "none";
            }
        };
        img.style.border = "2px solid blue";
        const select = document.getElementById("select");

        if (!avatarList[id - 1].owned) {
            select.innerHTML = "Purchase";
            cost.innerHTML = avatarList[id - 1].cost + " Points";

        } else {
            select.innerHTML = "Equip";
            cost.innerHTML = "Owned";

        }

    };

    const selectAvatar = evt => {
        const select = document.getElementById("select");
        if (select.innerHTML === "Equip") {
            saveAvatar();
        } else {
            buyAvatar();

        }
    };

    const saveAvatar = () => {
        equipAvatar(selected, userId)
            .then(() => {
                const newList = avatarList;
                const avatar = avatarList[selected];
                avatar.equipped = true;
                newList[selected] = avatar;
                setAvatarList([...newList]);
            })
    };

    const buyAvatar = () => {
        let tmp = userPoints;
        if (tmp > avatarList[selected-1].cost) {
            console.log("Cost");
            console.log(avatarList[selected-1].cost);
            tmp = tmp - avatarList[selected-1].cost;

            setUserPoints(tmp);
            addAvatar(selected, userId)
                .then(() => {
                    updatePoints(tmp, userId);
                    const avatar = avatarList[selected-1];
                    avatar.owned = true;
                    setAvatarList([...avatar]);
                })
                .catch((err) => console.log(err.toString()));
        }
    };

    return (
        <div>
            <div >

                <h1 className="display-5 fw-bolder offset-5" style={{ color: '#f7544d' }}>Avatars</h1>
            </div>
            <div className="offset-5" style={{ marginTop: '100px' }}>
                <h1 style={{ marginBottom: '100px' }}>Points: {userPoints}</h1>
                <h1 className="fw-bolder">Current Avatar:</h1>
                <img src={equipped != null && require(`./avatars/avatar${avatarList[equipped].microtransaction.id}.png`).default} height="100px" alt="current_avatar" className="ms-5" />

            </div>
            <h1 className="offset-2">Avatars</h1>
            <div className="container col-8 border border-dark offset-2" style={{ height: '400px' }}>

                {avatarList.length > 1 && avatarList.map(a => <input type="image" id={a.microtransaction.id} key={a.microtransaction.id} src={require(`./avatars/avatar${a.microtransaction.id}.png`).default} height="75px" alt="avatar" className="ms-4 my-2" onClick={optionselect} style={{ opacity: '0.4' }} />)}

                {/* <input type="image" id="1" src={require('./avatars/avatar1.png').default} height="75px" alt="current_avatar" className="ms-4 my-2" onClick={optionselect} style={{opacity: '0.4'}}/>
            <input type="image" id="2" src={require('./avatars/avatar2.png').default} height="75px" alt="current_avatar" className="ms-4 my-2" onClick={optionselect} style={{opacity: '0.4'}}/>
            <input type="image" id="3" src={require('./avatars/avatar3.png').default} height="75px" alt="current_avatar" className="ms-4 my-2" onClick={optionselect} style={{opacity: '0.4'}}/>
            <input type="image" id="4" src={require('./avatars/avatar4.png').default} height="75px" alt="current_avatar" className="ms-4 my-2" onClick={optionselect} style={{opacity: '0.4'}}/>
            <input type="image" id="5" src={require('./avatars/avatar5.png').default} height="75px" alt="current_avatar" className="ms-4 my-2" onClick={optionselect} style={{opacity: '0.4'}}/>
            <input type="image" id="6" src={require('./avatars/avatar6.png').default} height="75px" alt="current_avatar" className="ms-4 my-2" onClick={optionselect} style={{opacity: '0.4'}}/>
            <input type="image" id="7" src={require('./avatars/avatar7.png').default} height="75px" alt="current_avatar" className="ms-4 my-2" onClick={optionselect} style={{opacity: '0.4'}}/>
            <input type="image" id="8" src={require('./avatars/avatar8.png').default} height="75px" alt="current_avatar" className="ms-4 my-2" onClick={optionselect} style={{opacity: '0.4'}}/>
            <input type="image" id="9" src={require('./avatars/avatar9.png').default} height="75px" alt="current_avatar" className="ms-4 my-2" onClick={optionselect} style={{opacity: '0.4'}}/> */}


            </div>
            <h5 id="cost" className="offset-5">Owned</h5>
            <div className="offset-5 mt-5">
                <button id="select" className="btn btn-primary" onClick={selectAvatar}>Select</button>
                <Link to="/profile/:userId" className="btn btn-secondary ">Back</Link>
            </div>
        </div>
    );
};

export default Avatars;