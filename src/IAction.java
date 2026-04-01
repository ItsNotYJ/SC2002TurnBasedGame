public interface IAction {
    IAction executeTurn(Combatant user, Combatant target, BattleEngine engine);
}