import { Link } from "react-router-dom";
import { useEffect, useState } from "react/cjs/react.development";
import { addAvatar, equipAvatar } from "./apiServices/microtransactionApi";
import { fetchPts, fetchUserInfo } from "./apiServices/userApi";

function Avatars({ userData }) {
    const TOTAL_AVATARS = 9;
    const [userPoints, setUserPoints] = useState(0);
    const [equipped, setEquipped] = useState(0);
    const [avatarList, setAvatarList] = useState([]);
    const [userId, setUserId] = useState(0);
    const [selected, setSelected] = useState(0);

    useEffect(() => {
        fetchUserInfo(userData)
            .then((userInfo) => {
                console.log(userInfo);
                console.log(userData);
                const avatars = [];
                let j = 0;
                for (let i = 0; i < TOTAL_AVATARS; i++) {
                    if (userInfo.microtransactions.length > 0 && i == userInfo.microtransactions[j].microtransaction.id) {
                        avatars.push({ owned: true, ...userInfo.microtransactions[j] });
                        j++;
                    } else {
                        avatars.push({
                            owned: false,
                            equipped: false,
                            microtransaction: { id: i },
                            cost: i * 100
                        })
                    }


                    setUserId(userInfo.userId);
                    setUserPoints(userInfo.points);
                    for (let i = 0; i < avatars.length; i++) {
                        if (avatars[i].equipped == true) {
                            setEquipped(i);
                        }
                    }
                }
            })

            .catch((err) => console.log(err.toString()));

        // console.log(avatarList);
    }, []);

    useEffect(() => {
        // console.log(avatarList);
        for (let i = 0; i < avatarList.length; i++) {
            if (avatarList[i].owned) {
                document.getElementById(i).style.opacity = "1.0";
            }
            if (avatarList[i].equipped == true) {
                setEquipped(i);
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

        if (!avatarList[id].owned) {
            select.innerHTML = "Purchase";
            cost.innerHTML = avatarList[id].cost + " Points";

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
                const avatar = avatarList[selected];
                avatar.equipped = true;
                setAvatarList([...avatar]);
            })
    };

    const buyAvatar = () => {
        const tmp = userPoints;
        if (tmp > avatarList[selected].cost) {
            setUserPoints(tmp - avatarList[selected].cost);
            addAvatar(selected, userId)
                .then(() => {
                    saveAvatar();
                    const avatar = avatarList[selected];
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
                {/* <img src={require(`./avatars/avatar${avatarList[equipped].microtransaction.id}.png`).default} height="100px" alt="current_avatar" className="ms-5" /> */}
            </div>
            <h1 className="offset-2">Avatars</h1>
            <div className="container col-8 border border-dark offset-2" style={{ height: '400px' }}>

                {avatarList.map(a => <input type="image" id={a.microtransaction.id} key={a.microtransaction.id} src={require(`./avatars/avatar${a.microtransaction.id}.png`).default} height="75px" alt="avatar" className="ms-4 my-2" onClick={optionselect} style={{ opacity: '0.4' }} />)}

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